package com.orion.ops.entity.vo;

import com.orion.ops.entity.dto.SchedulerTaskRecordStatisticsDTO;
import com.orion.ops.utils.Utils;
import com.orion.utils.convert.TypeStore;
import lombok.Data;

/**
 * 调度任务机器执行统计
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/3/21 10:37
 */
@Data
public class SchedulerTaskMachineRecordStatisticsVO {

    /**
     * 机器id
     */
    private Long machineId;

    /**
     * 机器名称
     */
    private String machineName;

    /**
     * 调度次数
     */
    private Integer scheduledCount;

    /**
     * 成功次数
     */
    private Integer successCount;

    /**
     * 失败次数
     */
    private Integer failureCount;

    /**
     * 机器平均执行时长ms (成功)
     */
    private Long avgUsed;

    /**
     * 机器平均执行时长 (成功)
     */
    private String avgUsedInterval;

    static {
        TypeStore.STORE.register(SchedulerTaskRecordStatisticsDTO.class, SchedulerTaskMachineRecordStatisticsVO.class, p -> {
            SchedulerTaskMachineRecordStatisticsVO vo = new SchedulerTaskMachineRecordStatisticsVO();
            vo.setMachineId(p.getMachineId());
            vo.setMachineName(p.getMachineName());
            vo.setScheduledCount(p.getScheduledCount());
            vo.setSuccessCount(p.getSuccessCount());
            vo.setFailureCount(p.getFailureCount());
            vo.setAvgUsed(p.getAvgUsed());
            vo.setAvgUsedInterval(Utils.interval(p.getAvgUsed()));
            return vo;
        });
    }

}
