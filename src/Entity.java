public class Entity {
    private int attack;
    private int speed;
    private int health;
    private int x;
    private int y;
    private int width;
    private int height;
    public Entity(int attack,int speed,int health,int width, int height)
    {
        this.attack = attack;
        this.speed = speed;
        this.health = health;
        this.width = width;
        this.height = height;
        x = 242;
        y = 290;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
