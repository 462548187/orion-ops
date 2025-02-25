package com.orion.ops.task.impl;

import com.orion.lang.utils.Valid;
import com.orion.lang.utils.time.Dates;
import com.orion.ops.constant.SchedulerPools;
import com.orion.ops.constant.SerialType;
import com.orion.ops.dao.SchedulerTaskDAO;
import com.orion.ops.entity.domain.SchedulerTaskDO;
import com.orion.ops.handler.scheduler.ITaskProcessor;
import com.orion.ops.service.api.SchedulerTaskRecordService;
import com.orion.spring.SpringHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 调度任务实现
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/2/24 17:32
 */
@Slf4j
public class SchedulerTaskImpl implements Runnable {

    private static final SchedulerTaskDAO schedulerTaskDAO = SpringHolder.getBean(SchedulerTaskDAO.class);

    private static final SchedulerTaskRecordService schedulerTaskRecordService = SpringHolder.getBean(SchedulerTaskRecordService.class);

    private final Long id;

    public SchedulerTaskImpl(Long id) {
        this.id = id;
    }

    @Override
    public void run() {
        log.info("定制调度任务-触发 taskId: {}, time: {}", id, Dates.current());
        // 查询任务
        SchedulerTaskDO task = schedulerTaskDAO.selectById(id);
        Valid.notNull(task);
        // 创建任务
        Long recordId = schedulerTaskRecordService.createTaskRecord(id);
        // 执行任务
        ITaskProcessor processor = ITaskProcessor.with(recordId, SerialType.of(task.getSerializeType()));
        SchedulerPools.SCHEDULER_TASK_MAIN_SCHEDULER.execute(processor);
    }

}
