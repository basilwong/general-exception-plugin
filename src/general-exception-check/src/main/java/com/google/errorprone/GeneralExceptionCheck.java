package com.google.errorprone;

import static com.google.common.collect.Iterables.getLast;
import static com.google.errorprone.BugPattern.Category.JDK;
import static com.google.errorprone.BugPattern.LinkType.CUSTOM;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.matchers.Description.NO_MATCH;
import static com.google.errorprone.matchers.Matchers.instanceMethod;
import static com.google.errorprone.matchers.method.MethodMatchers.staticMethod;

import com.google.auto.service.AutoService;
import com.google.common.collect.Iterables;
import com.google.errorprone.BugPattern;
import com.google.errorprone.BugPattern.ProvidesFix;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.CatchTreeMatcher;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.fixes.SuggestedFix;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.source.tree.CatchTree;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

@AutoService(BugChecker.class)
@BugPattern(
        name = "GeneralExceptionCheck",
        category = JDK,
        summary = "Catch general exception not specific enough.",
        severity = ERROR,
        providesFix = ProvidesFix.REQUIRES_HUMAN_ATTENTION
        )
public class GeneralExceptionCheck extends BugChecker implements CatchTreeMatcher {

    @Override
    public Description matchCatch(CatchTree tree, VisitorState state) {

        String exception_str = tree.getParameter().toString();
        String exception_type = exception_str.split(" ")[0];
        // System.out.println("Exception:: " + exception_str);

        if (exception_type.equals("Exception")) {
            return describeMatch(tree.getParameter());
        }

        //TODO: Provide possible fix.
        // Fix fix;

        return Description.NO_MATCH;

    }


}
