package com.tianque.grid.workdairy.ui.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tianque.grid.business.domain.model.attachmentfile.AttachmentFileDomainService;
import com.tianque.grid.business.ui.vo.AttachmentFileVO;
import com.tianque.grid.ddd.ui.ResponseResult;
import com.tianque.grid.ddd.ui.ResponseResultFactory;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderAddCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderBindCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderDelCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderGetCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderQueryCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderSortCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureLeaderUpdateCommand;
import com.tianque.grid.gridmanage.application.service.ApplicationServiceRegistry;
import com.tianque.grid.gridmanage.constant.GridConstants;
import com.tianque.grid.gridmanage.domain.model.prefectureleader.PrefectureLeader;
import com.tianque.grid.gridmanage.domain.model.prefectureleader.PrefectureLeaderRepository;
import com.tianque.grid.gridmanage.domain.model.prefectureleader.PrefectureLeaderSearchHelper;
import com.tianque.grid.gridmanage.ui.mapperconverter.PrefectureLeaderMapperConverter;
import com.tianque.grid.gridmanage.ui.vo.PrefectureLeaderListVo;
import com.tianque.grid.gridmanage.ui.vo.PrefectureLeaderVo;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PrefectureLeaderController
 * @Description 辖区领导控制层
 * @Author ykb
 * @Date 2020/2/6
 */
@Api(
    tags = {"辖区领导管理"},
    description = "辖区领导管理相关接口"
)
@RestController
@RequestMapping("/prefectureLeaderManage")
@Slf4j
public class PrefectureLeaderController {

  @Autowired
  private ApplicationServiceRegistry applicationServiceRegistry;

  @Autowired
  private PrefectureLeaderRepository prefectureLeaderRepository;

  @Autowired
  private PrefectureLeaderMapperConverter prefectureLeaderMapperConverter;

  @Autowired
  private AttachmentFileDomainService attachmentFileDomainService;

  @Autowired
  private PrefectureLeaderSearchHelper prefectureLeaderSearchHelper;

  @ApiOperation(value = "列表信息", notes = "列表信息")
  @GetMapping(value = "/searchPrefecturePageInfo")
  public ResponseResult searchPrefecturePageInfo(
      @Validated @ModelAttribute PrefectureLeaderQueryCommand command) {
    List<PrefectureLeader> prefectureLeaders = prefectureLeaderRepository
        .findAll((Specification<PrefectureLeader>) (root, query, builder) ->
            builder.and(prefectureLeaderSearchHelper.conditionJudge(command, root, builder)));
    List<PrefectureLeaderVo> leaderVos = new ArrayList<>(prefectureLeaders.size());
    prefectureLeaders.sort(Comparator.comparing(PrefectureLeader::getSort));
    prefectureLeaders.forEach(prefectureLeader -> {
      PrefectureLeaderVo prefectureLeaderVo = prefectureLeaderMapperConverter
          .convert(prefectureLeader);
      List<AttachmentFileVO> attachmentFileVOList = attachmentFileDomainService
          .findAttachmentListByTypeAndRelId(GridConstants.GRID_CREW, prefectureLeader.getId());
      if (ObjectUtil.isNotNull(attachmentFileVOList) && attachmentFileVOList.size() > 0) {
        prefectureLeaderVo.setAttachmentFileVO(attachmentFileVOList.get(0));
      }
      leaderVos.add(prefectureLeaderVo);
    });
    PrefectureLeaderListVo leaderListVo = new PrefectureLeaderListVo();
    leaderListVo.setPrefectureLeaderVos(leaderVos);
    return ResponseResultFactory.successResult(leaderListVo);
  }

  @ApiOperation(value = "辖区领导-新增", notes = "添加辖区领导信息")
  @PostMapping(value = "/addPrefectureLeader")
  public ResponseResult addPrefectureLeader(@RequestBody PrefectureLeaderAddCommand command) {
    applicationServiceRegistry.getPrefectureLeaderApplicationService().addPrefectureLeader(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区领导-修改", notes = "修改辖区领导信息")
  @PostMapping(value = "/updatePrefectureLeader")
  public ResponseResult updatePrefectureLeader(@RequestBody PrefectureLeaderUpdateCommand command) {
    applicationServiceRegistry.getPrefectureLeaderApplicationService()
        .updatePrefectureLeader(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区领导-详情", notes = "根据id获取辖区领导信息")
  @GetMapping(value = "/getPrefectureInfo")
  public ResponseResult getPrefectureInfo(
      @Validated @ModelAttribute PrefectureLeaderGetCommand command) {
    PrefectureLeader prefectureLeader = prefectureLeaderRepository
        .getByIdAndIfDelete(command.getId(), false);
    if (ObjectUtil.isNull(prefectureLeader)) {
      return ResponseResultFactory.successResult();
    }
    PrefectureLeaderVo prefectureLeaderVo = prefectureLeaderMapperConverter
        .convert(prefectureLeader);
    List<AttachmentFileVO> attachmentFileVOList = attachmentFileDomainService
        .findAttachmentListByTypeAndRelId(
            GridConstants.PREFECTURE_INFO, prefectureLeader.getId());
    if (ObjectUtil.isNotNull(attachmentFileVOList) && attachmentFileVOList.size() > 0) {
      prefectureLeaderVo.setAttachmentFileVO(attachmentFileVOList.get(0));
    }
    return ResponseResultFactory.successResult(prefectureLeaderVo);
  }

  @ApiOperation(value = "辖区领导-绑定", notes = "绑定辖区领导信息")
  @PostMapping(value = "/bindPrefectureLeader")
  public ResponseResult bindPrefectureLeader(@RequestBody PrefectureLeaderBindCommand command) {
    applicationServiceRegistry.getPrefectureLeaderApplicationService()
        .bindPrefectureLeader(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区领导-删除", notes = "删除辖区领导信息")
  @PostMapping(value = "/deletePrefectureLeader")
  public ResponseResult deletePrefectureLeader(@RequestBody PrefectureLeaderDelCommand command) {
    applicationServiceRegistry.getPrefectureLeaderApplicationService()
        .deletePrefectureLeader(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区领导-排序", notes = "辖区领导排序")
  @PostMapping(value = "/sortPrefectureLeader")
  public ResponseResult sortPrefectureLeader(@RequestBody PrefectureLeaderSortCommand command) {
    applicationServiceRegistry.getPrefectureLeaderApplicationService()
        .sortPrefectureLeader(command);
    return ResponseResultFactory.successResult();
  }
}
