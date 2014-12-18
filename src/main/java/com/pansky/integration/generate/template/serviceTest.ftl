package ${packageName}.${moduleName}.service${subModuleName};
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.pansky.integration.common.test.SpringTransactionalContextTests;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName};
import ${packageName}.${moduleName}.service${subModuleName}.${ClassName}Service;

/**
 * ${functionName}ServiceTest
 * @author ${classAuthor}
 * @version ${classVersion}
 */
public class ${ClassName}ServiceTest extends SpringTransactionalContextTests {

	@Autowired
	private ${ClassName}Service ${className}Service;
	@Test
	public void testAll() {
		${ClassName} entity=new ${ClassName}();
		entity.setRemarks("remarks");
		//保存
		${className}Service.save(entity);
		//根据ID获取
		entity=${className}Service.get(entity.getId());
		//更新
		entity.setRemarks("remarks1");
		${className}Service.save(entity);
		//删除
		${className}Service.delete(entity.getId());
		TestCase.assertNotNull(entity.getCreateDate());
	}
	@Test
	public void testFind() {
		
	}
}
