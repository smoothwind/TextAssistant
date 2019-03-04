package itbuddy.plugins.group.Anctions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import itbuddy.plugins.group.ECaseConvertType;
import org.jetbrains.annotations.NotNull;

/**
 * @author smoothwind
 */
public class CaseConvertAnction extends AnAction {

    private ECaseConvertType convertType;

    public CaseConvertAnction() {
    }

    public CaseConvertAnction(String text, ECaseConvertType caseConvertType) {
        super(text);
        this.convertType = caseConvertType;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = anActionEvent.getProject();
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        String selectedText = selectionModel.getSelectedText();
        String replaceText = null;
        switch (this.convertType) {
            case LOWER:
                replaceText = selectedText.toLowerCase();
                break;
            case UPPER:
                replaceText = selectedText.toUpperCase();
                break;
            default:
                break;
        }
        replace(project, document, start, end, replaceText);

        selectionModel.removeSelection();
    }

    private void replace(Project project, Document document, int start, int end, String rep) {
        final String str = rep;
        if (start < end && !rep.isEmpty()) {
            WriteCommandAction.runWriteCommandAction(project, () ->
                    document.replaceString(start, end, str));
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null &&
                editor.getSelectionModel().hasSelection());
    }
}
