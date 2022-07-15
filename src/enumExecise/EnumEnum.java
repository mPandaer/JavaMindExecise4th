package enumExecise;

public class EnumEnum {
    public static void main(String[] args) {
        for (菜单 menu : 菜单.values()) {
            System.out.println(menu.name());
            for (菜单.食物 food : menu.foods) {
                System.out.format("%s ",food);
            }
            System.out.println();
            System.out.println("-------------------");
        }
    }
}

@SuppressWarnings("all")
enum 菜单 {
    菜单1(食物.零食.class),菜单2(食物.家常菜.class),菜单3(食物.烧烤.class);
    食物[] foods;
    private 菜单 (Class<? extends 食物> kind) {
        foods = kind.getEnumConstants();
    }

    @SuppressWarnings("all")
    interface 食物 {
        enum 零食 implements 食物 {
            辣条,薯片,冰淇淋;
        }
        enum 家常菜 implements 食物 {
            麻婆豆腐,酸菜鱼,水煮肉片;
        }
        enum 烧烤 implements 食物 {
            羊肉串,烤火腿,烤冷面;
        }
    }

}