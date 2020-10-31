package in.co.leminscate.LeminscateWebsite.api.auth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.leminscate.LeminscateWebsite.model.User;
import in.co.leminscate.LeminscateWebsite.services.UserService;

@RestController
public class LoginApi {

	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	@GetMapping
	public String hello(@RequestParam(name = "name", defaultValue = "Aniket Shelke") String name) {
		return "Hello " + name + "!!!";
	}

	@RequestMapping("/authenticate")
	@PostMapping
	public ResponseEntity<User> auth(@RequestParam("username") String name, @RequestParam("password") String password) {
		User user = userService.authenticate(name, password);

		if (user != null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.notFound().build();

	}

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> users(@PathVariable String name) {
		List<User> users = userService.getAll();
		if (!users.isEmpty())
			return ResponseEntity.ok(users);
		else
			return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> create(@RequestBody User user) {
		boolean create = userService.addUser(user);
		if (create)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.notFound().build();
	}

	@RequestMapping("/upload")
	@PostMapping
	public String upload(@RequestBody MultipartFile file) throws Exception {
		try {
			InputStream is = file.getInputStream();
			File saveFile = new File("D:/Tutorials/Spring Boot/" + file.getOriginalFilename());
			OutputStream os = new FileOutputStream(saveFile);
			os.write(is.readAllBytes());
			os.close();
			is.close();
			return "File is Uploaded";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/download")
	@GetMapping
	public ResponseEntity<Resource> download(@RequestParam(name = "filename") String name)
			throws MalformedURLException {
		Resource resource = null;
		resource = new UrlResource("file:///D:/Tutorials/Spring Boot/" + name);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);

	}
}
