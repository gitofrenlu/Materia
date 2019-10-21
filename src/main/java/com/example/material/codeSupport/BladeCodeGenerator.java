

package com.example.material.codeSupport;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.example.material.utils.Func;
import com.example.material.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class BladeCodeGenerator {
	private static final Logger log = LoggerFactory.getLogger(BladeCodeGenerator.class);
	private String systemName = "sword";
	private String codeName;
	private String serviceName = "blade-service";
	private String packageName = "org.springblade.test";
	private String packageDir;
	private String packageWebDir;
	private String[] tablePrefix = new String[]{"blade_"};
	private String[] includeTables = new String[]{"blade_test"};
	private String[] excludeTables = new String[0];
	private Boolean hasSuperEntity;
	private Boolean hasWrapper;
	private String[] superEntityColumns;
	private String tenantColumn;
	private Boolean isSwagger2;
	private String driverName;
	private String url;
	private String username;
	private String password;

	public void run() {
		Properties props = this.getProperties();
		AutoGenerator mpg = new AutoGenerator();
		GlobalConfig gc = new GlobalConfig();
		String outputDir = this.getOutputDir();
		String author = props.getProperty("author");
		gc.setOutputDir(outputDir);
		gc.setAuthor(author);
		gc.setFileOverride(true);
		gc.setOpen(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setSwagger2(this.isSwagger2);
		mpg.setGlobalConfig(gc);
		DataSourceConfig dsc = new DataSourceConfig();
		String driverName = Func.toStr(this.driverName, props.getProperty("spring.datasource.driver-class-name"));
		if (StringUtil.containsAny(driverName, new CharSequence[]{DbType.MYSQL.getDb()})) {
			dsc.setDbType(DbType.MYSQL);
			dsc.setTypeConvert(new MySqlTypeConvert());
		} else if (StringUtil.containsAny(driverName, new CharSequence[]{DbType.POSTGRE_SQL.getDb()})) {
			dsc.setDbType(DbType.POSTGRE_SQL);
			dsc.setTypeConvert(new PostgreSqlTypeConvert());
		} else {
			dsc.setDbType(DbType.ORACLE);
			dsc.setTypeConvert(new OracleTypeConvert());
		}

		dsc.setDriverName(driverName);
		dsc.setUrl(Func.toStr(this.url, props.getProperty("spring.datasource.url")));
		dsc.setUsername(Func.toStr(this.username, props.getProperty("spring.datasource.username")));
		dsc.setPassword(Func.toStr(this.password, props.getProperty("spring.datasource.password")));
		mpg.setDataSource(dsc);
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setTablePrefix(this.tablePrefix);
		if (this.includeTables.length > 0) {
			strategy.setInclude(this.includeTables);
		}

		if (this.excludeTables.length > 0) {
			strategy.setExclude(this.excludeTables);
		}

		if (this.hasSuperEntity) {
			strategy.setSuperEntityClass("org.springblade.core.mp.base.BaseEntity");
			strategy.setSuperEntityColumns(this.superEntityColumns);
			strategy.setSuperServiceClass("org.springblade.core.mp.base.BaseService");
			strategy.setSuperServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl");
		} else {
			strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
			strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
		}

		strategy.setSuperControllerClass("org.springblade.core.boot.ctrl.BladeController");
		strategy.setEntityBuilderModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		PackageConfig pc = new PackageConfig();
		pc.setModuleName((String)null);
		pc.setParent(this.packageName);
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);
		mpg.setCfg(this.getInjectionConfig());
		mpg.execute();
	}

	private InjectionConfig getInjectionConfig() {
		final String servicePackage = this.serviceName.split("-").length > 1 ? this.serviceName.split("-")[1] : this.serviceName;
		final Map<String, Object> map = new HashMap(16);
		InjectionConfig cfg = new InjectionConfig() {
			public void initMap() {
				map.put("codeName", BladeCodeGenerator.this.codeName);
				map.put("serviceName", BladeCodeGenerator.this.serviceName);
				map.put("servicePackage", servicePackage);
				map.put("tenantColumn", BladeCodeGenerator.this.tenantColumn);
				map.put("hasWrapper", BladeCodeGenerator.this.hasWrapper);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList();
		focList.add(new FileOutConfig("/templates/sql/menu.sql.vm") {
			public String outputFile(TableInfo tableInfo) {
				map.put("entityKey", tableInfo.getEntityName().toLowerCase());
				map.put("menuId", IdWorker.getId());
				map.put("addMenuId", IdWorker.getId());
				map.put("editMenuId", IdWorker.getId());
				map.put("removeMenuId", IdWorker.getId());
				map.put("viewMenuId", IdWorker.getId());
				return BladeCodeGenerator.this.getOutputDir() + "//sql/" + tableInfo.getEntityName().toLowerCase() + ".menu.mysql";
			}
		});
		focList.add(new FileOutConfig("/templates/entityVO.java.vm") {
			public String outputFile(TableInfo tableInfo) {
				return BladeCodeGenerator.this.getOutputDir() + "/" + BladeCodeGenerator.this.packageName.replace(".", "/") + "/vo/" + tableInfo.getEntityName() + "VO" + ".java";
			}
		});
		focList.add(new FileOutConfig("/templates/entityDTO.java.vm") {
			public String outputFile(TableInfo tableInfo) {
				return BladeCodeGenerator.this.getOutputDir() + "/" + BladeCodeGenerator.this.packageName.replace(".", "/") + "/dto/" + tableInfo.getEntityName() + "DTO" + ".java";
			}
		});
		if (this.hasWrapper) {
			focList.add(new FileOutConfig("/templates/wrapper.java.vm") {
				public String outputFile(TableInfo tableInfo) {
					return BladeCodeGenerator.this.getOutputDir() + "/" + BladeCodeGenerator.this.packageName.replace(".", "/") + "/wrapper/" + tableInfo.getEntityName() + "Wrapper" + ".java";
				}
			});
		}

		if (Func.isNotBlank(this.packageWebDir)) {
			if (Func.equals(this.systemName, "sword")) {
				focList.add(new FileOutConfig("/templates/sword/action.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/actions/" + tableInfo.getEntityName().toLowerCase() + ".js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/model.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/models/" + tableInfo.getEntityName().toLowerCase() + ".js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/service.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/services/" + tableInfo.getEntityName().toLowerCase() + ".js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/list.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/pages/" + StringUtil.upperFirst(servicePackage) + "/" + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + ".js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/add.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/pages/" + StringUtil.upperFirst(servicePackage) + "/" + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + "Add.js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/edit.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/pages/" + StringUtil.upperFirst(servicePackage) + "/" + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + "Edit.js";
					}
				});
				focList.add(new FileOutConfig("/templates/sword/view.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/pages/" + StringUtil.upperFirst(servicePackage) + "/" + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + "View.js";
					}
				});
			} else if (Func.equals(this.systemName, "saber")) {
				focList.add(new FileOutConfig("/templates/saber/api.js.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/api/" + servicePackage.toLowerCase() + "/" + tableInfo.getEntityName().toLowerCase() + ".js";
					}
				});
				focList.add(new FileOutConfig("/templates/saber/crud.vue.vm") {
					public String outputFile(TableInfo tableInfo) {
						return BladeCodeGenerator.this.getOutputWebDir() + "/views/" + servicePackage.toLowerCase() + "/" + tableInfo.getEntityName().toLowerCase() + ".vue";
					}
				});
			}
		}

		cfg.setFileOutConfigList(focList);
		return cfg;
	}

	private Properties getProperties() {
		Resource resource = new ClassPathResource("/templates/code.properties");
		Properties props = new Properties();

		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException var4) {
			var4.printStackTrace();
		}

		return props;
	}

	public String getOutputDir() {
		return (Func.isBlank(this.packageDir) ? System.getProperty("user.dir") + "/blade-ops/blade-develop" : this.packageDir) + "/src/main/java";
	}

	public String getOutputWebDir() {
		return (Func.isBlank(this.packageWebDir) ? System.getProperty("user.dir") : this.packageWebDir) + "/src";
	}

	private String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
		String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
		String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
		File viewDir = (new File(path)).getParentFile();
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}

		return path;
	}

	public BladeCodeGenerator() {
		this.hasSuperEntity = Boolean.FALSE;
		this.hasWrapper = Boolean.FALSE;
		this.superEntityColumns = new String[]{"create_time", "create_user", "create_dept", "update_time", "update_user", "status", "is_deleted"};
		this.tenantColumn = "tenant_id";
		this.isSwagger2 = Boolean.TRUE;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public String getPackageDir() {
		return this.packageDir;
	}

	public String getPackageWebDir() {
		return this.packageWebDir;
	}

	public String[] getTablePrefix() {
		return this.tablePrefix;
	}

	public String[] getIncludeTables() {
		return this.includeTables;
	}

	public String[] getExcludeTables() {
		return this.excludeTables;
	}

	public Boolean getHasSuperEntity() {
		return this.hasSuperEntity;
	}

	public Boolean getHasWrapper() {
		return this.hasWrapper;
	}

	public String[] getSuperEntityColumns() {
		return this.superEntityColumns;
	}

	public String getTenantColumn() {
		return this.tenantColumn;
	}

	public Boolean getIsSwagger2() {
		return this.isSwagger2;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public String getUrl() {
		return this.url;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	public void setCodeName(final String codeName) {
		this.codeName = codeName;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}

	public void setPackageDir(final String packageDir) {
		this.packageDir = packageDir;
	}

	public void setPackageWebDir(final String packageWebDir) {
		this.packageWebDir = packageWebDir;
	}

	public void setTablePrefix(final String[] tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public void setIncludeTables(final String[] includeTables) {
		this.includeTables = includeTables;
	}

	public void setExcludeTables(final String[] excludeTables) {
		this.excludeTables = excludeTables;
	}

	public void setHasSuperEntity(final Boolean hasSuperEntity) {
		this.hasSuperEntity = hasSuperEntity;
	}

	public void setHasWrapper(final Boolean hasWrapper) {
		this.hasWrapper = hasWrapper;
	}

	public void setSuperEntityColumns(final String[] superEntityColumns) {
		this.superEntityColumns = superEntityColumns;
	}

	public void setTenantColumn(final String tenantColumn) {
		this.tenantColumn = tenantColumn;
	}

	public void setIsSwagger2(final Boolean isSwagger2) {
		this.isSwagger2 = isSwagger2;
	}

	public void setDriverName(final String driverName) {
		this.driverName = driverName;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BladeCodeGenerator)) {
			return false;
		} else {
			BladeCodeGenerator other = (BladeCodeGenerator)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$systemName = this.getSystemName();
				Object other$systemName = other.getSystemName();
				if (this$systemName == null) {
					if (other$systemName != null) {
						return false;
					}
				} else if (!this$systemName.equals(other$systemName)) {
					return false;
				}

				Object this$codeName = this.getCodeName();
				Object other$codeName = other.getCodeName();
				if (this$codeName == null) {
					if (other$codeName != null) {
						return false;
					}
				} else if (!this$codeName.equals(other$codeName)) {
					return false;
				}

				Object this$serviceName = this.getServiceName();
				Object other$serviceName = other.getServiceName();
				if (this$serviceName == null) {
					if (other$serviceName != null) {
						return false;
					}
				} else if (!this$serviceName.equals(other$serviceName)) {
					return false;
				}

				label174: {
					Object this$packageName = this.getPackageName();
					Object other$packageName = other.getPackageName();
					if (this$packageName == null) {
						if (other$packageName == null) {
							break label174;
						}
					} else if (this$packageName.equals(other$packageName)) {
						break label174;
					}

					return false;
				}

				label167: {
					Object this$packageDir = this.getPackageDir();
					Object other$packageDir = other.getPackageDir();
					if (this$packageDir == null) {
						if (other$packageDir == null) {
							break label167;
						}
					} else if (this$packageDir.equals(other$packageDir)) {
						break label167;
					}

					return false;
				}

				Object this$packageWebDir = this.getPackageWebDir();
				Object other$packageWebDir = other.getPackageWebDir();
				if (this$packageWebDir == null) {
					if (other$packageWebDir != null) {
						return false;
					}
				} else if (!this$packageWebDir.equals(other$packageWebDir)) {
					return false;
				}

				if (!Arrays.deepEquals(this.getTablePrefix(), other.getTablePrefix())) {
					return false;
				} else if (!Arrays.deepEquals(this.getIncludeTables(), other.getIncludeTables())) {
					return false;
				} else if (!Arrays.deepEquals(this.getExcludeTables(), other.getExcludeTables())) {
					return false;
				} else {
					Object this$hasSuperEntity = this.getHasSuperEntity();
					Object other$hasSuperEntity = other.getHasSuperEntity();
					if (this$hasSuperEntity == null) {
						if (other$hasSuperEntity != null) {
							return false;
						}
					} else if (!this$hasSuperEntity.equals(other$hasSuperEntity)) {
						return false;
					}

					Object this$hasWrapper = this.getHasWrapper();
					Object other$hasWrapper = other.getHasWrapper();
					if (this$hasWrapper == null) {
						if (other$hasWrapper != null) {
							return false;
						}
					} else if (!this$hasWrapper.equals(other$hasWrapper)) {
						return false;
					}

					if (!Arrays.deepEquals(this.getSuperEntityColumns(), other.getSuperEntityColumns())) {
						return false;
					} else {
						label134: {
							Object this$tenantColumn = this.getTenantColumn();
							Object other$tenantColumn = other.getTenantColumn();
							if (this$tenantColumn == null) {
								if (other$tenantColumn == null) {
									break label134;
								}
							} else if (this$tenantColumn.equals(other$tenantColumn)) {
								break label134;
							}

							return false;
						}

						Object this$isSwagger2 = this.getIsSwagger2();
						Object other$isSwagger2 = other.getIsSwagger2();
						if (this$isSwagger2 == null) {
							if (other$isSwagger2 != null) {
								return false;
							}
						} else if (!this$isSwagger2.equals(other$isSwagger2)) {
							return false;
						}

						label120: {
							Object this$driverName = this.getDriverName();
							Object other$driverName = other.getDriverName();
							if (this$driverName == null) {
								if (other$driverName == null) {
									break label120;
								}
							} else if (this$driverName.equals(other$driverName)) {
								break label120;
							}

							return false;
						}

						Object this$url = this.getUrl();
						Object other$url = other.getUrl();
						if (this$url == null) {
							if (other$url != null) {
								return false;
							}
						} else if (!this$url.equals(other$url)) {
							return false;
						}

						label106: {
							Object this$username = this.getUsername();
							Object other$username = other.getUsername();
							if (this$username == null) {
								if (other$username == null) {
									break label106;
								}
							} else if (this$username.equals(other$username)) {
								break label106;
							}

							return false;
						}

						Object this$password = this.getPassword();
						Object other$password = other.getPassword();
						if (this$password == null) {
							if (other$password != null) {
								return false;
							}
						} else if (!this$password.equals(other$password)) {
							return false;
						}

						return true;
					}
				}
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BladeCodeGenerator;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $systemName = this.getSystemName();
		result = result * 59 + ($systemName == null ? 43 : $systemName.hashCode());
		Object $codeName = this.getCodeName();
		result = result * 59 + ($codeName == null ? 43 : $codeName.hashCode());
		Object $serviceName = this.getServiceName();
		result = result * 59 + ($serviceName == null ? 43 : $serviceName.hashCode());
		Object $packageName = this.getPackageName();
		result = result * 59 + ($packageName == null ? 43 : $packageName.hashCode());
		Object $packageDir = this.getPackageDir();
		result = result * 59 + ($packageDir == null ? 43 : $packageDir.hashCode());
		Object $packageWebDir = this.getPackageWebDir();
		result = result * 59 + ($packageWebDir == null ? 43 : $packageWebDir.hashCode());
		result = result * 59 + Arrays.deepHashCode(this.getTablePrefix());
		result = result * 59 + Arrays.deepHashCode(this.getIncludeTables());
		result = result * 59 + Arrays.deepHashCode(this.getExcludeTables());
		Object $hasSuperEntity = this.getHasSuperEntity();
		result = result * 59 + ($hasSuperEntity == null ? 43 : $hasSuperEntity.hashCode());
		Object $hasWrapper = this.getHasWrapper();
		result = result * 59 + ($hasWrapper == null ? 43 : $hasWrapper.hashCode());
		result = result * 59 + Arrays.deepHashCode(this.getSuperEntityColumns());
		Object $tenantColumn = this.getTenantColumn();
		result = result * 59 + ($tenantColumn == null ? 43 : $tenantColumn.hashCode());
		Object $isSwagger2 = this.getIsSwagger2();
		result = result * 59 + ($isSwagger2 == null ? 43 : $isSwagger2.hashCode());
		Object $driverName = this.getDriverName();
		result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
		Object $url = this.getUrl();
		result = result * 59 + ($url == null ? 43 : $url.hashCode());
		Object $username = this.getUsername();
		result = result * 59 + ($username == null ? 43 : $username.hashCode());
		Object $password = this.getPassword();
		result = result * 59 + ($password == null ? 43 : $password.hashCode());
		return result;
	}

	public String toString() {
		return "BladeCodeGenerator(systemName=" + this.getSystemName() + ", codeName=" + this.getCodeName() + ", serviceName=" + this.getServiceName() + ", packageName=" + this.getPackageName() + ", packageDir=" + this.getPackageDir() + ", packageWebDir=" + this.getPackageWebDir() + ", tablePrefix=" + Arrays.deepToString(this.getTablePrefix()) + ", includeTables=" + Arrays.deepToString(this.getIncludeTables()) + ", excludeTables=" + Arrays.deepToString(this.getExcludeTables()) + ", hasSuperEntity=" + this.getHasSuperEntity() + ", hasWrapper=" + this.getHasWrapper() + ", superEntityColumns=" + Arrays.deepToString(this.getSuperEntityColumns()) + ", tenantColumn=" + this.getTenantColumn() + ", isSwagger2=" + this.getIsSwagger2() + ", driverName=" + this.getDriverName() + ", url=" + this.getUrl() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
	}
}
