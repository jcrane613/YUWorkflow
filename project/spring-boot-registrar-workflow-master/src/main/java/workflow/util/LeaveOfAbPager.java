package workflow.util;


import workflow.model.LeaveOfAb;
import org.springframework.data.domain.Page;

public class LeaveOfAbPager {

	private final Page<LeaveOfAb> forms;

	public LeaveOfAbPager(Page<LeaveOfAb> forms) {
		this.forms = forms;
	}

	public int getPageIndex() {
		return forms.getNumber() + 1;
	}

	public int getPageSize() {
		return forms.getSize();
	}

	public boolean hasNext() {
		return forms.hasNext();
	}

	public boolean hasPrevious() {
		return forms.hasPrevious();
	}

	public int getTotalPages() {
		return forms.getTotalPages();
	}

	public long getTotalElements() {
		return forms.getTotalElements();
	}

	public boolean indexOutOfBounds() {
		return this.getPageIndex() < 0 || this.getPageIndex() > this.getTotalElements();
	}

}