package ${packageName}.${moduleName}.entity${subModuleName};

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.pansky.integration.common.persistence.IdEntity;

/**
 * ${functionName}Entity
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Entity
@Table(name = "${tableName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName} extends IdEntity<${ClassName}> {
	
	private static final long serialVersionUID = 1L;
	private String dm; 		// 代码
	private String name; 	// 名称

	public ${ClassName}() {
		super();
	}

	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=200)
	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}
	
}


