package step.learning.services.form;

import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public interface FormParseResult {
    Map<String, String> getFields() ; // usual form fields
    Map<String, FileItem> getFiles() ; // uploaded files
}
