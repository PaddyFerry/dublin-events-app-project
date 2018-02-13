import eventbrite
import entertainment
import runner
import json

f = open("test.json", "w")

f.write(json.dumps(runner.main() + entertainment.main() + eventbrite.main()))
f.close()
