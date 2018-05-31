package com.lichkin.framework.db.beans;

/**
 * 数据库资源定义类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class ActivitiR {

	public interface SysActivitiApiRequestLogStartProcessEntity {

		public static final int userId = 0x00010001;

		public static final int processCode = 0x00010002;

		public static final int compId = 0x00010003;

		public static final int locale = 0x00010004;

		public static final int token = 0x00010005;

		public static final int appKey = 0x00010006;

		public static final int clientType = 0x00010007;

		public static final int versionX = 0x00010008;

		public static final int versionY = 0x00010009;

		public static final int versionZ = 0x00010010;

		public static final int systemTag = 0x00010011;

		public static final int busId = 0x00010012;

		public static final int checkCode = 0x00010013;

		public static final int insertSystemTag = 0x00010014;

		public static final int insertTime = 0x00010015;

		public static final int insertLoginId = 0x00010016;

		public static final int updateSystemTag = 0x00010017;

		public static final int updateTime = 0x00010018;

		public static final int updateLoginId = 0x00010019;

		public static final int usingStatus = 0x00010020;

		public static final int id = 0x00010021;

	}

	public interface SysActivitiApiRequestLogCompleteProcessEntity {

		public static final int processInstanceId = 0x00020001;

		public static final int userId = 0x00020002;

		public static final int processCode = 0x00020003;

		public static final int compId = 0x00020004;

		public static final int locale = 0x00020005;

		public static final int token = 0x00020006;

		public static final int appKey = 0x00020007;

		public static final int clientType = 0x00020008;

		public static final int versionX = 0x00020009;

		public static final int versionY = 0x00020010;

		public static final int versionZ = 0x00020011;

		public static final int systemTag = 0x00020012;

		public static final int busId = 0x00020013;

		public static final int checkCode = 0x00020014;

		public static final int insertSystemTag = 0x00020015;

		public static final int insertTime = 0x00020016;

		public static final int insertLoginId = 0x00020017;

		public static final int updateSystemTag = 0x00020018;

		public static final int updateTime = 0x00020019;

		public static final int updateLoginId = 0x00020020;

		public static final int usingStatus = 0x00020021;

		public static final int id = 0x00020022;

	}

	public interface SysActivitiProcessConfigEntity {

		public static final int processCode = 0x00030001;

		public static final int processKey = 0x00030002;

		public static final int processType = 0x00030003;

		public static final int processName = 0x00030004;

		public static final int compId = 0x00030005;

		public static final int systemTag = 0x00030006;

		public static final int busId = 0x00030007;

		public static final int checkCode = 0x00030008;

		public static final int insertSystemTag = 0x00030009;

		public static final int insertTime = 0x00030010;

		public static final int insertLoginId = 0x00030011;

		public static final int updateSystemTag = 0x00030012;

		public static final int updateTime = 0x00030013;

		public static final int updateLoginId = 0x00030014;

		public static final int usingStatus = 0x00030015;

		public static final int id = 0x00030016;

	}

	public interface SysActivitiProcessTaskConfigEntity {

		public static final int processConfigId = 0x00040001;

		public static final int taskName = 0x00040002;

		public static final int userId = 0x00040003;

		public static final int userName = 0x00040004;

		public static final int step = 0x00040005;

		public static final int compId = 0x00040006;

		public static final int systemTag = 0x00040007;

		public static final int busId = 0x00040008;

		public static final int checkCode = 0x00040009;

		public static final int insertSystemTag = 0x00040010;

		public static final int insertTime = 0x00040011;

		public static final int insertLoginId = 0x00040012;

		public static final int updateSystemTag = 0x00040013;

		public static final int updateTime = 0x00040014;

		public static final int updateLoginId = 0x00040015;

		public static final int usingStatus = 0x00040016;

		public static final int id = 0x00040017;

	}

}