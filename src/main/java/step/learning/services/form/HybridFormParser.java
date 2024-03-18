package step.learning.services.form;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class HybridFormParser implements FormParseService {
    private static final int MEMORY_LIMIT = 10 * 1024 * 1024 ;  // 10 MB
    private static final int FILE_LIMIT   = 20 * 1024 * 1024 ;  // 20 MB
    private static final int FORM_LIMIT   = 30 * 1024 * 1024 ;  // 30 MB

    private final ServletFileUpload fileUpload ;

    @Inject
    public HybridFormParser() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository( new File(
                System.getProperty( "java.io.tmpdir" )
        ));
        factory.setSizeThreshold( MEMORY_LIMIT ) ;

        fileUpload = new ServletFileUpload( factory ) ;
        fileUpload.setFileSizeMax( FILE_LIMIT ) ;
        fileUpload.setSizeMax( FORM_LIMIT ) ;
    }

    @Override
    public FormParseResult parse(HttpServletRequest request) {
        final Map<String, String> fields = new HashMap<>();
        final Map<String, FileItem> files = new HashMap<>();

        boolean isMultipart = request
                .getHeader("Content-Type")
                .startsWith("multipart/form-data");

        if(isMultipart) {   // форма з файлами та полями. Користуємось Apache
            try {
                for( FileItem item : fileUpload.parseRequest( request ) ) {
                    if( item.isFormField() ) {   // це поле форми
                        fields.put(
                                item.getFieldName(),
                                item.getString( "UTF-8" )
                        );
                    }
                    else {   // це файл
                        files.put(
                                item.getFieldName(),
                                item
                        );
                    }
                }
            }
            catch (Exception ex) {
                System.err.println( ex.getMessage() ) ;
            }
        }
        else {   // звичайна форма, без файлів. Беремо засоби servlet API
            Enumeration<String> parameterNames = request.getParameterNames();
            while( parameterNames.hasMoreElements() ) {
                String parameterName = parameterNames.nextElement() ;
                fields.put( parameterName, request.getParameter( parameterName ) ) ;
            }
        }

        return new FormParseResult() {
            @Override public Map<String, String> getFields() { return fields; }
            @Override public Map<String, FileItem> getFiles() { return files; }
        };
    }
}