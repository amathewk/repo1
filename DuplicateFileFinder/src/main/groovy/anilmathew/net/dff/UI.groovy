package anilmathew.net.dff

import java.io.File;

import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL

import groovy.beans.Bindable

import javax.swing.*
//import javax.swing.tree.DefaultMutableTreeNode as TreeNode

class UI {

	static main(args) {
		//		def mboxes = [
		//			[name: "root@example.com", folders: [[name: "Inbox"], [name: "Trash"]]],
		//			[name: "test@foo.com", folders: [[name: "Inbox"], [name: "Trash"]]]
		//		]
		//		def swing = new SwingBuilder()
		//		JTree mboxTree
		//		swing.frame(title: 'Mailer', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE,
		//			size: [800, 600], show: true, locationRelativeTo: null) {
		//			lookAndFeel("system")
		//			menuBar() {
		//				menu(text: "File", mnemonic: 'F') {
		//					menuItem(text: "Exit", mnemonic: 'X', actionPerformed: {dispose() })
		//				}
		//			}
		//			splitPane {
		//				scrollPane(constraints: "left", preferredSize: [160, -1]) {
		//					mboxTree = tree(rootVisible: false)
		//				}
		//				splitPane(orientation:JSplitPane.VERTICAL_SPLIT, dividerLocation:280) {
		//					scrollPane(constraints: "top") { mailTable = table() }
		//					scrollPane(constraints: "bottom") { textArea() }
		//				}
		//			}
		//			["From", "Date", "Subject"].each { mailTable.model.addColumn(it) }
		//		}
		//
		//		mboxTree.model.root.removeAllChildren()
		//		mboxes.each {mbox ->
		//			def node = new TreeNode(mbox.name)
		//			mbox.folders.each { folder -> node.add(new TreeNode(folder.name)) }
		//			mboxTree.model.root.add(node)
		//		}
		//		mboxTree.model.reload(mboxTree.model.root)
		//
		//		return
		startUI()

	}

	static class UIModel {
		@Bindable String directoryToCheck = ""
		@Bindable String outputFilePath = ""
		@Bindable String message = ""
	}

	static def model = new UIModel()

	private static void startUI() {
		new SwingBuilder().edt {
			frame(title:'Duplicate File Checker', size:[300,200], show: true, defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE) {
				borderLayout()
				panel() {
					vbox {
						label(text:"Directory : ")
						textField(text: bind(target: model, targetProperty: "directoryToCheck"), columns: 20)
						label(text:"Output File : ")
						textField(text: bind(target: model, targetProperty: "outputFilePath"), columns: 20)
						hbox {
							label(text: "LEVEL: ")
							levelSelection = buttonGroup()
							radioButton("1", buttonGroup: levelSelection)
							radioButton("2", buttonGroup: levelSelection)
							radioButton("3", buttonGroup: levelSelection)
						}
						label(text: bind(source: model, sourceProperty: "message"))
					}
				}
				button(text:'RUN',
						actionPerformed: {runCheckForDuplicates()},
						constraints:BL.SOUTH)
			}
		}
	}

	private static void runCheckForDuplicates() {
		model.message = "Running..."
		System.out.println model.directoryToCheck
//		def root = new File ( "/media/250GB/soccer" )
//		def reportFile = new File ( "/home/amathew/Desktop/temp.txt" )
		def rootDir = new File (model.directoryToCheck)
		def reportFile = new File ( model.outputFilePath )
		UI duplicateFileFinder = new UI(reportFile)
		Map resultMap = duplicateFileFinder.findDuplicatesByExactNameAndSize(rootDir)
		duplicateFileFinder.printDuplicatesMap(resultMap, rootDir)
		//	duplicateFileFinder.findDuplicatesByExactName ( root )
		// duplicateFileFinder.findDuplicatesByNameFuzzyMatch ( root, 9 )
		model.message = "DONE!"
	}
}