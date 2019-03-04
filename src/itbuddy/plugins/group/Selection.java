package itbuddy.plugins.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import itbuddy.plugins.group.Anctions.CaseConvertAnction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author smoothwind
 */
public class Selection extends ActionGroup {
    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        return new AnAction[]{
                new CaseConvertAnction("Lowercase", ECaseConvertType.LOWER),
                new CaseConvertAnction("Uppercase", ECaseConvertType.UPPER),
        };
    }


}
