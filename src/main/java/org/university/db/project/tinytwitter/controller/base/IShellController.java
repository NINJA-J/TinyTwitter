package org.university.db.project.tinytwitter.controller.base;

import org.university.db.project.tinytwitter.controller.ResponseStatus;

public interface IShellController {
    ResponseStatus run(TwitterContext context);

    IShellController DEFAULT_NORMAL = context -> ResponseStatus.NORMAL;
}
