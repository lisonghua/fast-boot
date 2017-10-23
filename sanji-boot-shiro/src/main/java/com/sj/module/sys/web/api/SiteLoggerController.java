package com.sj.module.sys.web.api;

import com.sj.common.utils.FileOperateUtil;
import com.sj.exception.SanJiException;
import com.sj.module.sys.constant.RequestConstant;
import com.sj.module.sys.domain.SiteLogger;
import com.sj.module.sys.repository.SiteLoggerRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

/**
 * Created by yangrd on 2017/4/18.
 */
@RestController
@RequestMapping(RequestConstant.LOG_API)
public class SiteLoggerController {

    @Autowired
    private SiteLoggerRepository siteLoggerRepository;

    //此处以后可以添加更多查询维度
    @RequiresPermissions("sys:log:view")
    @GetMapping
    @Transactional(readOnly = true)
    public Page<SiteLogger> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "GET") String requestMethod, Date startDate, Date endDate, @PageableDefault(sort = "requestTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return siteLoggerRepository.findByUserLoginNameLikeAndRequestMethodAndRequestTimeBetween("%" + name + "%", requestMethod, startDate, endDate, pageable);
    }

    //excel 导出
    @RequiresPermissions("sys:log:export")
    @GetMapping("/export_excel")
    @Transactional(readOnly = true)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "GET") String requestMethod, Date startDate, Date endDate) {
        exportExcel(request, response, siteLoggerRepository.findByUserLoginNameLikeAndRequestMethodAndRequestTimeBetween("%" + name + "%", requestMethod, startDate, endDate));
    }

    //excel导出
    private void exportExcel(HttpServletRequest request, HttpServletResponse response, Collection<SiteLogger> collection) {
        ExportParams params = new ExportParams("后台日志监控", "日志数据", "后台日志监控");
        Workbook workbook = ExcelExportUtil.exportExcel(params, SiteLogger.class, collection);
        try {
            FileOperateUtil.download(request, response, "日志数据.xls", workbook);
        } catch (Exception e) {
            throw new SanJiException("excel导出失败");
        }
    }
}
