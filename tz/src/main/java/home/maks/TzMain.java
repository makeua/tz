package home.maks;

import home.maks.bean.app.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

public class TzMain implements Runnable {
    public static void main(String[] args) {
        TzMain main = new TzMain();
        main.run();
    }

    private final AnnotationConfigApplicationContext appContext;

    public TzMain() {
        this.appContext = Objects.requireNonNull(createAppContext());
    }

    @Override
    public void run() {
    }

    private AnnotationConfigApplicationContext createAppContext() {
        AnnotationConfigApplicationContext tempAppContext = null;
        try {
            tempAppContext = new AnnotationConfigApplicationContext();
            tempAppContext.registerShutdownHook();
            tempAppContext.scan(AppConfig.class.getPackage().getName());
            tempAppContext.refresh();

            AnnotationConfigApplicationContext appContext = tempAppContext;
            tempAppContext = null;
            return appContext;
        } finally {
            if (tempAppContext != null) {
                tempAppContext.close();
            }
        }
    }
}
