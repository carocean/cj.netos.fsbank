package cj.netos.inform;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.IContentReciever;
import cj.studio.ecm.net.IInputChannel;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.ecm.net.io.MemoryInputChannel;
import cj.studio.gateway.IRuntime;
import cj.studio.gateway.socket.Destination;
import cj.studio.gateway.socket.pipeline.IOutputSelector;
import cj.studio.gateway.socket.pipeline.IOutputer;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "$.netos.informer")
public class DefaultInformer implements Informer {
	@CjServiceRef(refByName = "$.output.selector")
	IOutputSelector selector;

	@CjServiceRef(refByName = "$.gateway.runtime")
	IRuntime runtime;

	@Override
	public Frame createFrame(String uri) throws CircuitException {
		return createFrame("get", uri, null);
	}

	@Override
	public Frame createFrame(String uri, byte[] content) throws CircuitException {
		return createFrame("post", uri, content);
	}
	@Override
	public Frame createFrame(String uri, Map<String, Object> contentMap) throws CircuitException {
		if(contentMap==null) {
			contentMap=new HashMap<String, Object>();
		}
		return createFrame("post", uri, new Gson().toJson(contentMap).getBytes());
	}
	@Override
	public Frame createFrame(String command, String uri, byte[] content) throws CircuitException {
		InformAddress address=new InformAddress(uri);
		IInputChannel in = new MemoryInputChannel();
		Frame f = new Frame(in, String.format("%s %s %s", command, address.url(), address.protocol()));
		IContentReciever reciever = new MemoryContentReciever();
		f.content().accept(reciever);
		f.head("Host", address.host());
		if (content != null) {
			in.done(content, 0, content.length);
		}
		return f;
	}

	@Override
	public void inform(Frame frame, Circuit circuit) throws CircuitException {
		String host = frame.head("Host");
		String restPath = String.format("rest://%s", host);
		String pt = frame.protocol();
		String uri = "";
		uri = pt.substring(0, pt.indexOf("/")).toLowerCase() + "://" + host;
		if (!runtime.containsValid(restPath)) {
			Destination dest = new Destination(restPath);
			dest.getUris().add(uri);
			runtime.addDestination(dest);
		}

		IOutputer out = null;
		try {
			out = selector.select(restPath);
		} catch (Exception e) {
			runtime.removeDestination(restPath);
			CJSystem.logging().error(getClass(), e.getMessage());
			throw e;
		}
		try {
			out.send(frame, circuit);
		} catch (Exception e) {
			ConnectException ce = searchConnectException(e);
			if (ce != null) {
				runtime.removeDestination(restPath);
			}
			CJSystem.logging().error(getClass(), e.getMessage());
			throw e;
		} finally {
			out.releasePipeline();
		}
	}

	protected ConnectException searchConnectException(Throwable e) {
		Stack<Throwable> stack = new Stack<Throwable>();
		ConnectException result = null;
		Throwable tmp = e;
		do {// 正序搜系统异常
			if (tmp instanceof ConnectException) {
				result = (ConnectException) tmp;
				break;
			}
			stack.push(tmp);
		} while ((tmp = tmp.getCause()) != null);
		// 如果不存在系统异常则反序找回路异常
		while (result == null && !stack.isEmpty()) {
			tmp = stack.pop();
			if (tmp instanceof ConnectException) {
				result = (ConnectException) tmp;
				break;

			}
		}
		return result;
	}
}
