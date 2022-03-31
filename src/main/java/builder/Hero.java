package builder;

public class Hero {

    private final String name;
    private final Weapon weapon;

    public Hero(Builder builder) {
        this.name = builder.name;
        this.weapon = builder.weapon;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public static class Builder {
        private final String name;
        private Weapon weapon;

        /**
         * Constructor.
         */
        public Builder(String name) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("name can not be null");
            }
            this.name = name;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", weapon=" + weapon +
                '}';
    }

}
