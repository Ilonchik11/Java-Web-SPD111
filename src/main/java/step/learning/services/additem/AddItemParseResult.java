package step.learning.services.additem;

import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public interface AddItemParseResult {
    Map<String, String> getFields() ; // usual form fields
    Map<String, FileItem> getFiles() ; // uploaded files
}
