package io.swagger.client.api;

@javax.annotation.Generated(value = "com.lish.dongfang.fastboot.codegen.FastbootcodegenGenerator", date = "2017-11-23T11:31:18.758+08:00")

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
