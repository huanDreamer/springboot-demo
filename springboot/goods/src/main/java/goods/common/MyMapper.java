package goods.common;

/**
 * 
 * @author 张欢
 * @date 2016年10月8日 下午3:15:10
 */

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/** * 被继承的Mapper，一般业务Mapper继承它 * */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
