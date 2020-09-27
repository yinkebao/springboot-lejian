package com.tianque.grid.workdairy.ui.mapperconverter;

import com.tianque.grid.ddd.ui.modelmapper.AbstractModelMapperConverter;
import com.tianque.grid.gridmanage.domain.model.gridcrew.GridCrew;
import com.tianque.grid.gridmanage.ui.vo.GridCrewVo;
import org.springframework.stereotype.Component;

/**
 * @ClassName GridCrewMapperConverter
 * @Description GridCrew 实体类转换器
 * @Author ykb
 * @Date 2020/2/11
 */
@Component
public class GridCrewMapperConverter extends
    AbstractModelMapperConverter<GridCrew, GridCrewVo> {

}
