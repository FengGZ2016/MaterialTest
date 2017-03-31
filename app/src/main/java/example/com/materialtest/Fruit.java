package example.com.materialtest;

/**
 * Created by 国富小哥 on 2017/3/28.
 * 水果的实体类
 */

public class Fruit {
    private String name;
    private int Imageid;

    Fruit(String name,int Imageid){
        this.name=name;
        this.Imageid=Imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageid() {
        return Imageid;
    }

    public void setImageid(int imageid) {
        Imageid = imageid;
    }
}
