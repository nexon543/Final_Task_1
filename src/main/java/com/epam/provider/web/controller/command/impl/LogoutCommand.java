package com.epam.provider.web.controller.command.impl;

import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Gleb Akseonov
 */
public class LogoutCommand implements ActionCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult result = new CommandResult(CommandResult.CommandResultState.REDIRECT_INDEX);
    req.getSession().invalidate();
    return result;
  }
}
