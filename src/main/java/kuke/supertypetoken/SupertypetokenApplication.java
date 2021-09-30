package kuke.supertypetoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SupertypetokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupertypetokenApplication.class, args);
	}


	@RestController
	public static class MyController {

		@RequestMapping("/")
		public List<User> users() {
			return Arrays.asList(new User("a"),
					new User("b"), new User("c"));
		}
	}

	public static class User {
		String name;

		public User() {
		}

		public User(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
