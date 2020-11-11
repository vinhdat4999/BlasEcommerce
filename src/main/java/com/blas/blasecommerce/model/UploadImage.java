package com.blas.blasecommerce.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadImage {
	    private CommonsMultipartFile[] fileDatas;
	 
	    public CommonsMultipartFile[] getFileDatas() {
	        return fileDatas;
	    }
	 
	    public void setFileDatas(CommonsMultipartFile[] fileDatas) {
	        this.fileDatas = fileDatas;
	    }
}
