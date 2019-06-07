package cj.netos.inform;

import java.util.Map;

import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;

public interface Informer {
	static String KEY_SERVICE_NAME = "$.netos.informer";

	/**
	 * 执行通知。<br>
	 * 即可向远程网关的stub方法通知，也可向任意的http服务通知<br>
	 * 如果欲取回响应，则：
	 * 
	 * <pre>
	 * 
	 * MemoryOutputChannel oc = new MemoryOutputChannel();
	 * Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
	 * informer.inform(frame, circuit);
	 * byte[] b = oc.readFully();
	 * System.out.println(new String(b));
	 * </pre>
	 * 
	 * @param frame
	 * @param circuit
	 * @throws CircuitException
	 */
	void inform(Frame frame, Circuit circuit) throws CircuitException;

	/**
	 * 创建侦。默认指令为：get
	 * 
	 * @param informAddress
	 * @return
	 * @throws CircuitException
	 */
	Frame createFrame(String informAddress) throws CircuitException;

	/**
	 * 创建侦。默认指令为：post
	 * 
	 * @param informAddress
	 * @param content       请求内容。
	 * 
	 *                      <pre>
	 * 如果远程服务是stub服务方法的内容参数，例如参数名为info，内容必须用以下格式写：
	 * {
	 * 		"info":{}
	 * }
	 * 将之转换为bytes即可
	 *                      </pre>
	 * 
	 * @return
	 * @throws CircuitException
	 */
	Frame createFrame(String informAddress, byte[] content) throws CircuitException;

	/**
	 * 创建侦
	 * 
	 * @param command       请求命令
	 * @param informAddress 通知地址
	 * @param content       请求内容。
	 * 
	 *                      <pre>
	 * 如果远程服务是stub服务方法的内容参数，例如参数名为info，内容必须用以下格式写：
	 * {
	 * 		"info":{}
	 * }
	 * 将之转换为bytes即可
	 *                      </pre>
	 * 
	 * @return
	 * @throws CircuitException
	 */
	Frame createFrame(String command, String informAddress, byte[] content) throws CircuitException;

	/**
	 * 创建侦，默认指令为:post<br>
	 * 专门为兼容调用stub服务而设，建议只要传内容均用此格式，尽量远程接收目标对内容解析的统一
	 * @param uri
	 * @param contentMap 请求内容
	 *                   
	 * @return
	 * @throws CircuitException
	 */
	Frame createFrame(String uri, Map<String, Object> contentMap) throws CircuitException;

}
