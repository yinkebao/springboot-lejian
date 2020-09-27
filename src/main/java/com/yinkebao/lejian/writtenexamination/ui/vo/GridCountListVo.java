package com.tianque.grid.workdairy.ui.vo;

import com.tianque.grid.ddd.ui.vo.Vo;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName GridCountListVo
 * @Description
 * @Author ykb
 * @Date 2020/2/12
 */
@Setter
@Getter
@NoArgsConstructor
public class GridCountListVo extends Vo {

  List<GridCountVo> gridCountVos = new ArrayList<>();
}
