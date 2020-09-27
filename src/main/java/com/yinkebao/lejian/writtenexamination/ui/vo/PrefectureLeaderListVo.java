package com.tianque.grid.workdairy.ui.vo;

import com.tianque.grid.ddd.ui.vo.Vo;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName PrefectureLeaderListVo
 * @Description
 * @Author ykb
 * @Date 2020/2/12
 */
@Setter
@Getter
@NoArgsConstructor
public class PrefectureLeaderListVo extends Vo {

  List<PrefectureLeaderVo> prefectureLeaderVos;
}
