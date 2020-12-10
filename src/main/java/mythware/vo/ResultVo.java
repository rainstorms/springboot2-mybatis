package mythware.vo;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ResultVo {
    /*
    返回编码,0为成功，-1为失败,-2失败后reload，不同正数为其它成功情况，不同负数为其它失败情况
     */
    private int code;
    /*
    主要为失败时，携带的失败信息.
    不建议用这个来携带成功时的其它信息.
     */
    private String message;

    /*
     携带的数据
     */
    private Object data;

    /**
     * 生成失败的返回对象。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo fail(int code, String message, Object data) {
        return ResultVo.builder().code(code).message(message).data(data).build();
    }


    /**
     * 生成失败的返回对象。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo fail(String message) {
        return ResultVo.builder().code(-1).message(message).build();
    }

    /**
     * 生成失败的返回对象。
     *
     * @param message 失败消息
     * @param data    数据对象
     * @return 返回对象
     */
    public static ResultVo fail(String message, Object data) {
        return ResultVo.builder().code(-1).message(message).data(data).build();
    }

    /**
     * 生成失败的返回对象。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo fail(int code, String message) {
        return ResultVo.builder().code(code).message(message).build();
    }

    /**
     * 失败确认，刷新页面
     *
     * @param message
     * @return
     */
    public static ResultVo reload(String message) {
        return ResultVo.builder().code(-2).message(message).build();
    }

    /**
     * 生成成功的返回对象。
     *
     * @param message 成功消息
     * @return 返回对象
     */
    public static ResultVo ok(String message) {
        return ResultVo.builder().code(0).message(message).build();
    }

    /**
     * 生成成功的返回对象。
     *
     * @param message 成功消息
     * @param data    数据对象
     * @return 返回对象
     */
    public static ResultVo ok(String message, Object data) {
        return ResultVo.builder().code(0).message(message).data(data).build();
    }

    /**
     * 返回是否是成功。
     *
     * @return true 成功
     */
    public boolean isNotOk() {
        return code < 0;
    }

    /**
     * 带成功编码的返回.
     *
     * @param succCode
     * @param message
     * @return
     */
    public static ResultVo succ(int succCode, String message) {
        return ResultVo.builder().code(succCode).message(message).build();
    }

    /**
     * 生成需二次确认的返回对象。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo confirm(String message) {
        return ResultVo.builder().code(1).message(message).build();
    }

    /**
     * 生成需二次确认的返回对象。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo confirm(String message, Object data) {
        return ResultVo.builder().code(1).message(message).data(data).build();
    }

    /**
     * 生成需二次确认的返回对象(需要备注)。
     *
     * @param message 失败消息
     * @return 返回对象
     */
    public static ResultVo confirmWithRemark(String message, Object data) {
        return ResultVo.builder().code(100).message(message).data(data).build();
    }

}
