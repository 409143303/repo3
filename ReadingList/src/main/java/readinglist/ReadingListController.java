package readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {
	
	private ReadingListRepostitory readingListRepostitory;
	private AmazonProperties amazonProperties;
	
	@Autowired
	public ReadingListController(ReadingListRepostitory readingListRepostitory,
			AmazonProperties amazonProperties) {
		this.readingListRepostitory = readingListRepostitory;
		this.amazonProperties = amazonProperties;
	}
	
	@RequestMapping(value = "/ReadingList/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		
		List<Book> readingList = readingListRepostitory.findByReader(reader);
 		if(readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amzonID",  amazonProperties.getAssociateId());
		}
		return "readingList";
	}
	
	@RequestMapping(value = "/ReadingList/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepostitory.save(book);
		return "redirect:/ReadingList/{reader}";
	}
	
	
	
}
