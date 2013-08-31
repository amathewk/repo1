package net.filebuilder

import java.util.Map;

import net.filebuilder.FileBuilder;
import org.apache.log4j.*

class FileBuilder extends BuilderSupport {
	
	public static FileBuilder newInstance() {
		return new FileBuilder()
	}

	@Override
	protected void setParent(Object parent, Object child) {
		println "setParent with parent : $parent and child : $child"	
	}

	@Override
	protected Object createNode(Object name) {
		println "createNode with name : $name"
		File f = new File(getCurrentParent(), (String)name)
		f.mkdirs()
		return f;
	}

	@Override
	protected Object createNode(Object name, Object value) {
		println "create with name : $name and value : $value"
		println "currentParent : $currentParent"
		File f = new File(getCurrentParent(), (String)name)
		println "fullpath : ${f.absolutePath}, exists : ${f.exists()}"
		f.createNewFile()
		f << value
		println "fullpath : ${f.absolutePath}, exists : ${f.exists()}"
		return f;
	}

	@Override
	protected Object createNode(Object name, Map attributes) {
		println "create with name : $name and attributes : $attributes"
		File f = new File((String)attributes.parentPath, (String)name)
		f.mkdirs()
		return f
	}

	@Override
	protected Object createNode(Object name, Map attributes, Object value) {
		println "create with name : $name, attributes : $attributes and value : $value"
		File f = new File((String)attributes.parentPath, (String)name)
		f << value
		return f
	}
	
	File getCurrentParent() {
		return (File)getCurrent()
	}
}
