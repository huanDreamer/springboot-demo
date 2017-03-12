package goods.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import goods.pojo.TbGoods;

/**
 * 验证，客户端传过来的商品数据是否合法
 * @author 张欢
 */
@Component
public class GoodsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TbGoods.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotNull", "商品id不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotNull", "商品名称不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotNull", "商品价格不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotNull", "生产日期不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num", "NotNull", "库存不能为空");
		
		TbGoods goods = (TbGoods)target;
		
		if(null != goods.getNum() && goods.getNum() < 0) {
			errors.rejectValue("num", "Min", "库存不能小于0");
		}
		if(null != goods.getPrice() && goods.getPrice() < 0) {
			errors.rejectValue("price", "Min", "商品价格不能小于0");
		}
		
	}

}
