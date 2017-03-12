package goods.common;

/**
 * Api 返回的数据类型
 * 
 * @author 张欢
 * @date 2016年9月28日 下午1:34:42
 */

public class ApiResponse<T> {

	/**
	 * 结果码，0 表示成功,其他表示失败
	 */
	private int code;

	/**
	 * 返回的消息
	 */
	private String msg;

	/**
	 * 返回的数据
	 */
	private T data;

	public ApiResponse(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

}
