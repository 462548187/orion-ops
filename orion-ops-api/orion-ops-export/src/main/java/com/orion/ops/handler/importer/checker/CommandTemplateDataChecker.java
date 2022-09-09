package com.orion.ops.handler.importer.checker;

import com.orion.ops.constant.ImportType;
import com.orion.ops.dao.CommandTemplateDAO;
import com.orion.ops.entity.domain.CommandTemplateDO;
import com.orion.ops.entity.importer.CommandTemplateImportDTO;
import com.orion.ops.entity.vo.data.DataImportCheckVO;
import com.orion.spring.SpringHolder;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 命令模板 数据检查器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/9/9 16:35
 */
public class CommandTemplateDataChecker extends AbstractDataChecker<CommandTemplateImportDTO, CommandTemplateDO> {

    private static final CommandTemplateDAO commandTemplateDAO = SpringHolder.getBean(CommandTemplateDAO.class);

    public CommandTemplateDataChecker(Workbook workbook) {
        super(ImportType.COMMAND_TEMPLATE, workbook);
    }

    @Override
    protected DataImportCheckVO checkImportData(List<CommandTemplateImportDTO> rows) {
        // 检查数据合法性
        this.validImportRows(rows);
        // 通过名称查询模板
        List<CommandTemplateDO> presentTemplates = this.getImportRowsPresentValues(rows,
                CommandTemplateImportDTO::getName,
                commandTemplateDAO, CommandTemplateDO::getTemplateName);
        // 检查数据是否存在
        this.checkImportRowsPresent(rows, CommandTemplateImportDTO::getName,
                presentTemplates, CommandTemplateDO::getTemplateName, CommandTemplateDO::getId);
        // 设置导入检查数据
        return this.setImportCheckRows(rows);
    }

}
