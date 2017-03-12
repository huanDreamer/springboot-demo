package goods.service;

import java.util.List;

import goods.pojo.TbGoods;

/**
 * 商品service
 * 
 * @author 张欢
 *
 */
public interface GoodsService {

	/**
	 * 增加一件商品
	 * 
	 * @param 要增加的商品
	 * @return true 表示成功 false表示
	 */
	public boolean insert(TbGoods goods);

	/**
	 * 列出所有的商品
	 * 
	 * @return 所有商品
	 */
	public List<TbGoods> listAll();

	/**
	 * 删除商品
	 * 
	 * @param 要删除的商品的
	 *            id
	 * @return 如果删除成功就返回 true 删除失败就返回 false
	 */
	public boolean delete(int id);

	/**
	 * 根据商品 id 查询商品信息
	 * 
	 * @param 要查询的商品
	 *            id
	 * @return 商品信息
	 */
	public TbGoods getGoods(int id);

	/**
	 * 修改商品详情
	 * 
	 * @param 修改后的商品
	 * @return true 表示更新成功，false 表示更新失败
	 */
	public boolean update(TbGoods goods);
}
