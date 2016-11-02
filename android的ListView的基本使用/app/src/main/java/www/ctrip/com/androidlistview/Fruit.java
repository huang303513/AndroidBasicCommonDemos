package www.ctrip.com.androidlistview;

/**
 * 项目名称：android的ListView的基本使用
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/31 下午10:55
 * 修改人：huangchengdu
 * 修改时间：16/8/31 下午10:55
 * 修改备注：
 */

/**
 * 定义一个实体类,作为 ListView 适配器的适配类型.
 */
public class Fruit {
    private String name;
    private int imageId;
    public  Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
