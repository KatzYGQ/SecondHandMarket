interface List {
    public void add();
    public void remove();
}

class ArrayList implements List {
    @Override
    public void add(Object obj) {
        //add this object
    }
    
    @Override
    public void remove(Object obj) {
        //remove this object
    }
}

class LinkedList implements List {
    @Override
    public void add(Object obj) {
        //add this object
    }
    
    @Override
    public void remove(Object obj) {
        //remove this object
    }
}



import com.google.inject.Guice;
import com.google.inject.Injector;
class Cart {
    
    List<Item> list;
    
    @Injection
    public Cart(List list) {
        this.list = list;
    }
    
    public void addItem(Item item) {
        list.add(item);
    }
}

class User {
    Cart cart;
    List<Item> list;
    @Injection
    public User(Cart cart) {
        this.cart = cart;
        this.list = cart.list;
    }
}


public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ListModule());
        List list = injector.getInstance(List.class);
        Cart cart = new Cart(list);
        cart.add(item);
    }
    
    private void method() {
        List list = injector.getInstance(List.class);
        List list2 = injector.getInstance(List.class);
        List list3 = injector.getInstance(List.class);
    }
}


import com.google.inject.AbstractModule;
public class ListModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(List.class).to(ArrayList.class).to(Singleton.class);
    }
}


