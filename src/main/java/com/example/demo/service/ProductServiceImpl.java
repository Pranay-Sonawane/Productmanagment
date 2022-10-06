package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.dao.ProductdaoImpl;
import com.example.demo.entity.Product;
import com.example.demo.sort.ProductIdComparator;
import com.example.demo.sort.ProductNameComparator;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductdaoImpl dao;
	
	@Override
	public boolean saveProduct(Product product) {
		
		if(product.getProductId()==null) {
			
		String id = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new java.util.Date());
		
		product.setProductId(id);
	}
		boolean isAdded = dao.saveProduct(product);
		
		return isAdded;
	}
	
	
	@Override
	public Product getProductById(String productId) {
		Product product = dao.getProductById(productId);
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = dao.getAllProduct();
		return list;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDelete = dao.deleteProductById(productId);
		return isDelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated = dao.updateProduct(product);
		return isUpdated;
	}

	@Override
	public List<Product> sortProductById_Asc(String sortBy) {
		List<Product> list = getAllProduct();
		if(sortBy.equalsIgnoreCase("productId")) {
			Collections.sort(list, new ProductIdComparator());
		}
		return list;
		 
	

}

	@Override
	public List<Product> sortProductByName_Des(String sortBy) {
		List<Product> list = getAllProduct();
		if(sortBy.equalsIgnoreCase("productName")) {
			Collections.sort(list, new ProductNameComparator());
			Collections.reverse(list);
	}
		return list;
	}

	
	@Override
	public Product getMaxPriceProduct() {
         List<Product> allProduct = getAllProduct();
		
		Product product = allProduct.stream()
				.max(Comparator.comparingDouble(Product::getProductPrice)).get();
		
		return product;	
	}

	@Override
	public double countSumOfProductPrice() {
		
         List<Product> allProduct = getAllProduct();
		
		double sum = allProduct.stream()
				.mapToDouble(Product::getProductPrice).sum();
		return sum;
	}

	@Override
	public int getTotalCountOfProducts() {
		
		 List<Product> allProduct = getAllProduct(); 	

			return allProduct.size();
	}

public List<Product> readExcel(String filePath){
		
		FileInputStream fis = null;
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		
		try {
			fis = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();
			
			while(rows.hasNext()) {
				Row row = rows.next();
				product = new Product();
				Thread.sleep(1);
				String id = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new java.util.Date());
				product.setProductId(id);
				Iterator<Cell> cells = row.cellIterator();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
				int column = cell.getColumnIndex();
				
				switch (column) {
				case 0:{
		            product.setProductName(cell.getStringCellValue());
					break;
				}
				case 1:{
					product.setProductPrice(cell.getNumericCellValue());
					break;
				}
				case 2:{
					product.setProductQty((int) cell.getNumericCellValue());
					break;
				}
				case 3:{
					product.setProductType(cell.getStringCellValue());
					break;
				}
				
				
				}
				}
				list.add(product);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	
}
	@Override
	public int uploadSheet(CommonsMultipartFile file, HttpSession httpSession) {
		String path = httpSession.getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		int count = 0;
		FileOutputStream fos = null;
		byte[] data = file.getBytes();
		try {
			System.out.println(path);
			fos = new FileOutputStream(new File(path + File.separator +filename));
			fos.write(data);
			
			List<Product> list = readExcel(path + File.separator + filename);
			  count = dao.uploadProductList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}   
