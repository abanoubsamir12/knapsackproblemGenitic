public class chromosome {
    public String content;
    public int weight;
     public  int value;
     public  int rankVal;

    public void setContent(String content) {
        this.content = content;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public chromosome(String content) {
        this.content = content;
        weight = 0;
        value=0;
        rankVal=0;
    }
    public chromosome()
    {
        weight =0;
        value=0;
        rankVal=0;
    }
    public  chromosome(chromosome c){
        this.content = c.content;
        this.weight = c.weight;
        this.value = c.value;
    }

    @Override
    public String toString() {
        return "chromosome{" +
                "content='" + content + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                ", rankVal=" + rankVal +
                '}';
    }
}
