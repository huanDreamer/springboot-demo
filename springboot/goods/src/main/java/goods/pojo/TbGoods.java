package goods.pojo;

import java.util.Date;

import javax.persistence.Id;

public class TbGoods {

	@Id
	// @NotNull
	private Integer id;

	private String name;

	// @NotNull(message="价格不能为空")
	// @Min(value=0,message="价格必须大于0")
	private Float price;

	private Date date;
	
	// @NotNull(message="库存不能为空")
	// @Min(value=0,message="库存必须大于0")
	private Integer num;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "TbGoods [id=" + id + ", name=" + name + ", price=" + price
				+ ", date=" + date + ", num=" + num + "]";
	}

}