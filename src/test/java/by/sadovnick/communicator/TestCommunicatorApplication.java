package by.sadovnick.communicator;

import org.springframework.boot.SpringApplication;

public class TestCommunicatorApplication {

    public static void main(String[] args) {
        SpringApplication.from(CommunicatorApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
