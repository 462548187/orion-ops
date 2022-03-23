package com.orion.ops.service.api;

import com.orion.ops.entity.vo.SchedulerTaskRecordStatisticsVO;
import com.orion.ops.entity.vo.StatisticsVO;

/**
 * 统计service
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/1/20 14:13
 */
public interface StatisticsService {

    /**
     * 获取首页统计信息
     *
     * @return 统计信息
     */
    StatisticsVO homeStatistics();

    /**
     * 获取调度任务统计
     *
     * @param taskId 任务id
     * @return 统计
     */
    SchedulerTaskRecordStatisticsVO schedulerTaskStatistic(Long taskId);

}
