package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: fanbopeng
 * @Date: 2018/11/17 18:26
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T>{


    private long total;
    private List<T> rows;
}
