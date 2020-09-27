package com.tianque.grid.workdairy.ui.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tianque.grid.business.domain.model.attachmentfile.AttachmentFileDomainService;
import com.tianque.grid.business.ui.vo.AttachmentFileVO;
import com.tianque.grid.ddd.ui.ResponseResult;
import com.tianque.grid.ddd.ui.ResponseResultFactory;
import com.tianque.grid.gridmanage.application.command.GridCrewAddCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewBindCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewCountCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewDelCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewGetCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewQueryCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewSortCommand;
import com.tianque.grid.gridmanage.application.command.GridCrewUpdateCommand;
import com.tianque.grid.gridmanage.application.service.ApplicationServiceRegistry;
import com.tianque.grid.gridmanage.constant.GridConstants;
import com.tianque.grid.gridmanage.domain.model.gridcrew.GridCrew;
import com.tianque.grid.gridmanage.domain.model.gridcrew.GridCrewRepository;
import com.tianque.grid.gridmanage.domain.model.gridcrew.GridCrewSearchHelper;
import com.tianque.grid.gridmanage.ui.mapperconverter.GridCrewMapperConverter;
import com.tianque.grid.gridmanage.ui.vo.GridCountListVo;
import com.tianque.grid.gridmanage.ui.vo.GridCountVo;
import com.tianque.grid.gridmanage.ui.vo.GridCrewListVo;
import com.tianque.grid.gridmanage.ui.vo.GridCrewVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GridCrewController
 * @Description 网格员控制层
 * @Author ykb
 * @Date 2020/2/6
 */
@Api(
    tags = {"网格员管理"},
    description = "网格员管理相关接口"
)
@RestController
@RequestMapping("/gridCrewManage")
@Slf4j
public class GridCrewController {

  @Autowired
  private ApplicationServiceRegistry applicationServiceRegistry;

  @Autowired
  private GridCrewRepository gridCrewRepository;

  @Autowired
  private GridCrewMapperConverter gridCrewMapperConverter;

  @Autowired
  private AttachmentFileDomainService attachmentFileDomainService;

  @Autowired
  private GridCrewSearchHelper gridCrewSearchHelper;

  @ApiOperation(value = "列表信息", notes = "列表信息")
  @RequestMapping(value = "/searchPrefecturePageInfo", method = RequestMethod.GET)
  public ResponseResult searchPrefecturePageInfo(
      @Validated @ModelAttribute GridCrewQueryCommand command) {
    List<GridCrew> gridCrews = gridCrewRepository
        .findAll((Specification<GridCrew>) (root, query, builder) ->
            builder.and(gridCrewSearchHelper.conditionJudge(command, root, builder)));
    List<GridCrewVo> leaderVos = new ArrayList<>(gridCrews.size());
    gridCrews.sort(Comparator.comparing(GridCrew::getSort));
    gridCrews.forEach(gridCrew -> {
      GridCrewVo gridCrewVo = gridCrewMapperConverter.convert(gridCrew);
      List<AttachmentFileVO> attachmentFileVOList = attachmentFileDomainService
          .findAttachmentListByTypeAndRelId(GridConstants.GRID_CREW, gridCrew.getId());
      if (ObjectUtil.isNotNull(attachmentFileVOList) && attachmentFileVOList.size() > 0) {
        gridCrewVo.setAttachmentFileVO(attachmentFileVOList.get(0));
      }
      leaderVos.add(gridCrewVo);
    });
    GridCrewListVo gridCrewListVo = new GridCrewListVo();
    gridCrewListVo.setGridCrewVos(leaderVos);
    return ResponseResultFactory.successResult(gridCrewListVo);
  }

  @ApiOperation(value = "网格员-新增", notes = "添加网格员信息")
  @PostMapping(value = "/addGridCrew")
  public ResponseResult addGridCrew(@RequestBody GridCrewAddCommand command) {
    applicationServiceRegistry.getGridCrewApplicationService().addGridCrew(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "网格员-修改", notes = "修改网格员信息")
  @PostMapping(value = "/updateGridCrew")
  public ResponseResult updateGridCrew(@RequestBody GridCrewUpdateCommand command) {
    applicationServiceRegistry.getGridCrewApplicationService().updateGridCrew(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "网格员-详情", notes = "根据id获取网格员信息")
  @GetMapping(value = "/getPrefectureInfo")
  public ResponseResult getPrefectureInfo(@Validated @ModelAttribute GridCrewGetCommand command) {
    GridCrew gridCrew = gridCrewRepository
        .getByIdAndIfDelete(command.getId(), false);
    if (ObjectUtil.isNull(gridCrew)) {
      return ResponseResultFactory.successResult();
    }
    GridCrewVo gridCrewVo = gridCrewMapperConverter.convert(gridCrew);
    List<AttachmentFileVO> attachmentFileVOList = attachmentFileDomainService
        .findAttachmentListByTypeAndRelId(
            GridConstants.GRID_CREW, gridCrew.getId());
    if (ObjectUtil.isNotNull(attachmentFileVOList) && attachmentFileVOList.size() > 0) {
      gridCrewVo.setAttachmentFileVO(attachmentFileVOList.get(0));
    }
    return ResponseResultFactory.successResult(gridCrewVo);
  }

  @ApiOperation(value = "网格员-绑定", notes = "绑定网格员信息")
  @PostMapping(value = "/bindGridCrew")
  public ResponseResult bindGridCrew(@RequestBody GridCrewBindCommand command) {
    applicationServiceRegistry.getGridCrewApplicationService().bindGridCrew(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "网格员-删除", notes = "删除网格员信息")
  @PostMapping(value = "/deleteGridCrew")
  public ResponseResult deleteGridCrew(@RequestBody GridCrewDelCommand command) {
    applicationServiceRegistry.getGridCrewApplicationService().deleteGridCrew(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "网格员-排序", notes = "网格员排序")
  @PostMapping(value = "/sortGridCrew")
  public ResponseResult sortGridCrew(@RequestBody GridCrewSortCommand command) {
    applicationServiceRegistry.getGridCrewApplicationService().sortGridCrew(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "网格汇总", notes = "网格汇总")
  @RequestMapping(value = "gridCount", method = RequestMethod.POST)
  public ResponseResult gridCount(@Validated @RequestBody GridCrewCountCommand command) {
    GridCountListVo gridCountListVo = new GridCountListVo();
    GridCountVo gridCountVo;
    Integer gridNum, gridLeaderNum;
    for (Long deptId : command.getDeptIds()) {
      gridCountVo = new GridCountVo();
      gridNum = gridCrewRepository.getGridNum(deptId);
      gridLeaderNum = gridCrewRepository.getGridLeaderNum(deptId);
      gridCountVo.setGridCrewNum(ObjectUtil.isNotNull(gridNum) ? gridNum : 0);
      gridCountVo.setGridLeaderNum(ObjectUtil.isNotNull(gridLeaderNum) ? gridLeaderNum : 0);
      gridCountListVo.getGridCountVos().add(gridCountVo);
    }
    return ResponseResultFactory.successResult(gridCountListVo);
  }

}
