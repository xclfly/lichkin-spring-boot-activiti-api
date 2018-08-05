package com.lichkin;

import com.lichkin.framework.defines.enums.LKCodeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 错误编码
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCodes implements LKCodeEnum {

	no_auth(900000),

	process_type_config_error(900001),

	start_process_failed(900002);

	/** 编码 */
	private final Integer code;

}
