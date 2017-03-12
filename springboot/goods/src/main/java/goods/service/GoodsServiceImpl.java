package goods.service;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goods.mapper.TbGoodsMapper;
import goods.pojo.TbGoods;

/**
 * @author 张欢
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	TbGoodsMapper goodsMapper;
	
	Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean insert(TbGoods goods) {

		try {
			goodsMapper.insert(goods);

			return true;

		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
		}

		return false;
	}

	
	@Override
	public List<TbGoods> listAll() {

		List<TbGoods> list = goodsMapper.selectAll();

		return list;
	}

	@Override
	public boolean delete(int id) {

		try {
			goodsMapper.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
		}

		return false;
	}

	@Override
	public TbGoods getGoods(int id) {

		TbGoods goods = goodsMapper.selectByPrimaryKey(id);
		return goods;
	}

	@Override
	public boolean update(TbGoods goods) {

		try {

			goodsMapper.updateByPrimaryKey(goods);
			return true;

		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
		}

		return false;
	}

}
