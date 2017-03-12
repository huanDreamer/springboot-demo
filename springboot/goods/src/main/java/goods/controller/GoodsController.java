package goods.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import goods.common.ApiResponse;
import goods.pojo.TbGoods;
import goods.service.GoodsService;
import goods.validators.GoodsValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="商品管理api", description="有关商品的crud操作")
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	/**
	 * 表单验证
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(new GoodsValidator());
	}

	/**
	 * 列出所有商品
	 * 
	 * @return
	 */
	@ApiOperation(value = "列出所有的商品", notes = "参数：无；返回值：返回商品列表，json格式", response=ApiResponse.class)
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public ApiResponse<List<TbGoods>> listAll() {
		List<TbGoods> list = goodsService.listAll();
		return new ApiResponse<List<TbGoods>>(0, "success", list);
	}

	/**
	 * 新增商品
	 * 
	 * @param goods
	 * @return
	 */
	@ApiOperation(value = "添加商品", notes = "参数：要添加的商品信息；返回：成功/失败")
	@ApiImplicitParams({@ApiImplicitParam(name="goods", paramType="body", value="要添加的商品信息", required=true)})
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ApiResponse<TbGoods> insert(@RequestBody @Valid TbGoods goods,
			BindingResult result) {
		

		/*result.getAllErrors().forEach(r->{
			System.out.println("code:"+r.getCode()+ "  filed:" + r.getObjectName() +"   msg:"+r.getDefaultMessage());
		});*/
		

		// 如果表单验证不通过就返回第一条错误信息
		if(result.hasErrors()) {
			return new ApiResponse<TbGoods>(1, result.getAllErrors().get(0).getDefaultMessage(), null);
		}

		if (goodsService.insert(goods) == true) {
			return new ApiResponse<TbGoods>(0, "success", null);
		} else {
			return new ApiResponse<TbGoods>(1, "fail", null);
		}
	}

	/**
	 * 根据 id 删除指定商品
	 * 
	 * @param id
	 *            要删除出的商品 id
	 * @return success 或者 error
	 */
	@ApiOperation(value = "删除商品", notes = "参数：要删除的商品id；返回：成功/失败")
	@ApiImplicitParam(name="id", paramType="body", value="要删除的商品id", required=true)
	@RequestMapping(value="delete/{id}", method=RequestMethod.GET)
	public ApiResponse<TbGoods> delete(@PathVariable int id) {

		if (goodsService.delete(id) == true) {
			return new ApiResponse<TbGoods>(0, "success", null);
		}
		return new ApiResponse<TbGoods>(1, "fail", null);
	}

	/**
	 * 根据商品 id 返回商品信息
	 * 
	 * @param id
	 *            要查询的商品id
	 * @return 商品信息
	 */
	@ApiOperation(value = "查询一件商品", notes = "参数：要查询的商品id；返回：商品信息", response=ApiResponse.class)
	@ApiImplicitParam(name="id", paramType="query", value="要查询的商品id", required=true)
	@RequestMapping(value="getGoods/{id}", method=RequestMethod.GET)
	public ApiResponse<TbGoods> getGoods(@PathVariable int id) {
		return new ApiResponse<TbGoods>(0, "success", goodsService.getGoods(id));
	}

	/**
	 * 修改商品
	 * 
	 * @param goods
	 *            修改后的商品信息
	 * @return 修改成功或者是失败
	 */
	@ApiOperation(value = "根据id修改商品信息", notes = "参数：修改后的商品信息；返回：成功/失败")
	@ApiImplicitParam(name="goods", paramType="body", value="修改后的商品信息", required=true)
	@RequestMapping(value="update", method=RequestMethod.POST)
	public ApiResponse<TbGoods> update(@RequestBody @Valid TbGoods goods,
			BindingResult result) {
		

		// 如果表单验证不通过就返回第一条错误信息
		if(result.hasErrors()) {
			return new ApiResponse<TbGoods>(1, result.getAllErrors().get(0).getDefaultMessage(), null);
		}

		if (goodsService.update(goods) == true) {
			return new ApiResponse<TbGoods>(0, "success", null);
		} else {
			return new ApiResponse<TbGoods>(1, "fail", null);
		}

	}

}
