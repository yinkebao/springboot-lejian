package com.tianque.grid.workdairy.ui.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tianque.grid.business.domain.model.attachmentfile.AttachmentFileDomainService;
import com.tianque.grid.business.ui.vo.AttachmentFileVO;
import com.tianque.grid.ddd.ui.ResponseResult;
import com.tianque.grid.ddd.ui.ResponseResultFactory;
import com.tianque.grid.gridmanage.application.command.PrefectureInfoAddCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureInfoGetCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureInfoQueryCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureInfoUpdateCommand;
import com.tianque.grid.gridmanage.application.command.PrefectureIntroUpdateCommand;
import com.tianque.grid.gridmanage.application.service.ApplicationServiceRegistry;
import com.tianque.grid.gridmanage.constant.GridConstants;
import com.tianque.grid.gridmanage.domain.model.prefectureinfo.PrefectureInfo;
import com.tianque.grid.gridmanage.domain.model.prefectureinfo.PrefectureInfoRepository;
import com.tianque.grid.gridmanage.domain.model.prefectureinfo.PrefectureInfoSearchHelper;
import com.tianque.grid.gridmanage.ui.mapperconverter.PrefectureInfoMapperConverter;
import com.tianque.grid.gridmanage.ui.mapperconverter.PrefectureInfoPageMapperConverter;
import com.tianque.grid.gridmanage.ui.vo.PrefectureInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * @ClassName PrefectureInfoController
 * @Description 辖区信息控制层
 * @Author ykb
 * @Date 2020/2/6
 */
@Api(
    tags = {"辖区信息管理"},
    description = "辖区信息管理相关接口"
)
@RestController
@RequestMapping("/prefectureInfoManage")
@Slf4j
public class PrefectureInfoController {

  @Autowired
  private ApplicationServiceRegistry applicationServiceRegistry;

  @Autowired
  private PrefectureInfoRepository prefectureInfoRepository;

  @Autowired
  private PrefectureInfoMapperConverter prefectureInfoMapperConverter;

  @Autowired
  private PrefectureInfoPageMapperConverter prefectureInfoPageMapperConverter;

  @Autowired
  private AttachmentFileDomainService attachmentFileDomainService;

  @Autowired
  private PrefectureInfoSearchHelper prefectureInfoSearchHelper;

  @ApiOperation(value = "分页信息", notes = "分页信息")
  @GetMapping(value = "/searchPrefecturePageInfo")
  public ResponseResult searchPrefecturePageInfo(
      @Validated @ModelAttribute PrefectureInfoQueryCommand command) {
    Pageable pageable = PageRequest
        .of(command.getPageInfo().getCurPage() - 1, command.getPageInfo().getRow(),
            new Sort(Sort.Direction.DESC, "createDate"));
    Page<PrefectureInfo> prefectureInfo = prefectureInfoRepository
        .findAll((Specification<PrefectureInfo>) (root, query, builder) ->
                builder.and(prefectureInfoSearchHelper.conditionJudge(command, root, builder)),
            pageable);
    return ResponseResultFactory
        .successResult(prefectureInfoPageMapperConverter.convert(prefectureInfo));
  }

  @ApiOperation(value = "辖区信息-新增", notes = "添加辖区信息")
  @PostMapping(value = "/addPrefectureInfo")
  public ResponseResult addPrefectureInfo(@RequestBody PrefectureInfoAddCommand command) {
    applicationServiceRegistry.getPrefectureInfoApplicationService().addPrefectureInfo(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区信息-修改介绍", notes = "修改辖区信息介绍")
  @PostMapping(value = "/updateIntroduction")
  public ResponseResult updateIntroduction(@RequestBody PrefectureIntroUpdateCommand command) {
    applicationServiceRegistry.getPrefectureInfoApplicationService().updateIntroduction(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区信息-修改", notes = "修改辖区信息")
  @PostMapping(value = "/updatePrefectureInfo")
  public ResponseResult updatePrefectureInfo(@RequestBody PrefectureInfoUpdateCommand command) {
    applicationServiceRegistry.getPrefectureInfoApplicationService().updatePrefectureInfo(command);
    return ResponseResultFactory.successResult();
  }

  @ApiOperation(value = "辖区信息-详情", notes = "根据id获取辖区信息")
  @GetMapping(value = "/getPrefectureInfo")
  public ResponseResult getPrefectureInfo(@RequestBody PrefectureInfoGetCommand command) {
    PrefectureInfo prefectureInfo = prefectureInfoRepository
        .getByIdAndIfDelete(command.getId(), false);
    if (ObjectUtil.isNull(prefectureInfo)) {
      return ResponseResultFactory.successResult();
    }
    PrefectureInfoVo prefectureInfoVo = prefectureInfoMapperConverter.convert(prefectureInfo);
    List<AttachmentFileVO> attachmentFileVOList = attachmentFileDomainService
        .findAttachmentListByTypeAndRelId(
            GridConstants.PREFECTURE_INFO, prefectureInfo.getId());
    if (ObjectUtil.isNotNull(attachmentFileVOList) && attachmentFileVOList.size() > 0) {
      prefectureInfoVo.setAttachmentFileVO(attachmentFileVOList.get(0));
    }
    return ResponseResultFactory.successResult(prefectureInfoVo);
  }


}
