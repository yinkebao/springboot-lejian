package com.tianque.grid.workdairy.ui.mapperconverter;

import com.tianque.grid.ddd.ui.dto.PageDto;
import com.tianque.grid.ddd.ui.modelmapper.PageModelMapperConverter;
import com.tianque.grid.gridmanage.domain.model.prefectureinfo.PrefectureInfo;
import com.tianque.grid.gridmanage.ui.vo.PrefectureInfoVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @ClassName PrefectureInfoPageMapperConverter
 * @Description PrefectureInfo 分页信息转换器
 * @Author ykb
 * @Date 2020/2/7
 */
@Component
public class PrefectureInfoPageMapperConverter extends PrefectureInfoMapperConverter implements
    PageModelMapperConverter<PrefectureInfo, PrefectureInfoVo> {

  @Autowired
  private PrefectureInfoMapperConverter prefectureInfoMapperConverter;

  @Override
  public PageDto<PrefectureInfoVo> convert(List<PrefectureInfo> list, Integer size, Integer curPage,
      Long total) {
    List<PrefectureInfoVo> prefectureInfoVos = new ArrayList<>();
    for (PrefectureInfo prefectureInfo : list) {
      PrefectureInfoVo prefectureInfoVo = prefectureInfoMapperConverter.convert(prefectureInfo);
      prefectureInfoVos.add(prefectureInfoVo);
    }
    return new PageDto<>(prefectureInfoVos, size, curPage, total);
  }

  public PageDto<PrefectureInfoVo> convert(Page<PrefectureInfo> page) {
    return convert(page.getContent(), page.getPageable().getPageSize(),
        page.getPageable().getPageNumber() + 1, page.getTotalElements());
  }
}
