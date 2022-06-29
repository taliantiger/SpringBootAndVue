package org.talian.controller.emp;

import org.talian.bean.Employee;
import org.talian.bean.Position;
import org.talian.bean.RespBean;
import org.talian.common.EmailRunnable;
//import org.talian.common.poi.PoiUtils;
import org.talian.service.DepartmentService;
import org.talian.service.EmpService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.talian.service.JobLevelService;
import org.talian.service.PositionService;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by sang on 2018/1/12.
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmpService empService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PositionService positionService;

    @Autowired
    JobLevelService jobLevelService;


    /**
     * 这个ExecutorService库是个接口，所以不需要 Autowired,我这添加了Autowired 会报错。
     * 但是作者添加了Autowired 也没有报错
     *
     * 参考：https://stackoverflow.com/questions/42907553/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-ap
     * I had the same problem I removed the @Autowired Annotation from the controller.
     * If your repository is a class then the Autowired Annotation is needed to use the repository,
     * but when it is an interface you do not need to add the @Autowired Annotation from my experience.
     */
//    @Autowired
    ExecutorService executorService;

    @Autowired
    TemplateEngine templateEngine;

//    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/basicdata", method = RequestMethod.GET)
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = new HashMap<>();
        map.put("nations", empService.getAllNations());
        map.put("politics", empService.getAllPolitics());
        map.put("deps", departmentService.getDepByPid(-1L));
        map.put("positions", positionService.getAllPos());
        map.put("joblevels", jobLevelService.getAllJobLevels());
        map.put("workID", String.format("%08d", empService.getMaxWorkID() + 1));
        return map;
    }

    @RequestMapping("/maxWorkID")
    public String maxWorkID() {
        return String.format("%08d", empService.getMaxWorkID() + 1);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public RespBean addEmp(Employee employee) {
        if (empService.addEmp(employee) == 1) {
            List<Position> allPos = positionService.getAllPos();
            for (Position allPo : allPos) {
                if (allPo.getId() == employee.getPosId()) {
                    employee.setPosName(allPo.getName());
                }
            }
            executorService.execute(new EmailRunnable(employee,
                    javaMailSender, templateEngine));
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public RespBean updateEmp(Employee employee) {
        if (empService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpById(@PathVariable String ids) {
        if (empService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            Long politicId, Long nationId, Long posId,
            Long jobLevelId, String engageForm,
            Long departmentId, String beginDateScope) {
        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPage(page, size,
                keywords,politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, beginDateScope);
        Long count = empService.getCountByKeywords(keywords, politicId, nationId,
                posId,jobLevelId, engageForm, departmentId, beginDateScope);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }


    /**
     * 以下两个方法暂时先不管了，不知道为什么，导入作者原版的pom.xml文件，
     * 尝试更新包，依然无法识别  "import org.talian.common.poi.PoiUtils;"
     */
//    @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> exportEmp() {
//        return PoiUtils.exportEmp2Excel(empService.getAllEmployees());
//    }

//    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
//    public RespBean importEmp(MultipartFile file) {
//        List<Employee> emps = PoiUtils.importEmp2List(file,
//                empService.getAllNations(), empService.getAllPolitics(),
//                departmentService.getAllDeps(), positionService.getAllPos(),
//                jobLevelService.getAllJobLevels());
//        if (empService.addEmps(emps) == emps.size()) {
//            return RespBean.ok("导入成功!");
//        }
//        return RespBean.error("导入失败!");
//    }


}
